package org.beesden.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class TrimFilter implements Filter {

	static final String[] START_TRIM_AFTER = { "<html" };
	static final String[] STOP_TRIM_AFTER = { "</html" };

	private static PrintWriter createTrimWriter(final HttpServletResponse response) throws IOException {
		return new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"), true) {
			private StringBuilder builder = new StringBuilder();
			private boolean trim = true;

			@Override
			public void flush() {
				synchronized (builder) {
					BufferedReader reader = new BufferedReader(new StringReader(builder.toString()));
					String line = null;

					try {
						while ((line = reader.readLine()) != null) {
							if (line.trim().length() > 0) {
								if (startTrim(line)) {
									trim = true;
									out.write(line);
								} else if (trim) {
									if (line.endsWith(" ")) {
										out.write(line.trim() + " ");
									} else if (line.startsWith(" ")) {
										out.write(" " + line.trim());
									} else {
										out.write(line.trim());
									}
									if (stopTrim(line)) {
										trim = false;
										println();
									}
								} else {
									out.write(line);
									println();
								}
							}
						}
					} catch (IOException e) {
						setError();
					}

					builder = new StringBuilder();
					super.flush();
				}
			}

			private boolean startTrim(String line) {
				for (String match : START_TRIM_AFTER) {
					if (line.contains(match)) {
						return true;
					}
				}
				return false;
			}

			private boolean stopTrim(String line) {
				for (String match : STOP_TRIM_AFTER) {
					if (line.contains(match)) {
						return true;
					}
				}
				return false;
			}

			@Override
			public void write(char[] chars, int offset, int length) {
				builder.append(chars, offset, length);
				this.flush();
			}

			@Override
			public void write(int c) {
				builder.append((char) c);
			}

			@Override
			public void write(String string, int offset, int length) {
				builder.append(string, offset, length);
				this.flush();
			}
		};
	}

	private static HttpServletResponse wrapResponse(final HttpServletResponse response, final PrintWriter writer) {
		return new HttpServletResponseWrapper(response) {
			@Override
			public PrintWriter getWriter() throws IOException {
				return writer;
			}
		};
	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (response instanceof HttpServletResponse) {
			HttpServletResponse httpres = (HttpServletResponse) response;
			chain.doFilter(request, wrapResponse(httpres, createTrimWriter(httpres)));
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}
}