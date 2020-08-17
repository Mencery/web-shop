package com.plekhanov.filter.compression;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * wrapper {@link HttpServletResponse} for gzip compression
 */
class GZipServletResponseWrapper extends HttpServletResponseWrapper {

    private GZipServletOutputStream gzipOutputStream = null;
    private PrintWriter printWriter = null;

    /**
     * call super constructor
     *
     * @param response - {@link HttpServletResponse}
     */
    public GZipServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    /**
     * {@link PrintWriter}.close does not throw exceptions.
     *
     * @throws IOException -hence no try-catch block.
     */
    public void close() throws IOException {

        if (this.printWriter != null) {
            this.printWriter.close();
        }

        if (this.gzipOutputStream != null) {
            this.gzipOutputStream.close();
        }
    }


    /**
     * flushes {@link OutputStream} or {@link PrintWriter}
     *
     * @throws IOException - because working with {@link OutputStream}
     */

    @Override
    public void flushBuffer() throws IOException {
        if (this.printWriter != null) {
            this.printWriter.flush();
        }

        IOException exception1 = null;
        try {
            if (this.gzipOutputStream != null) {
                this.gzipOutputStream.flush();
            }
        } catch (IOException e) {
            exception1 = e;
        }

        IOException exception2 = null;
        try {
            super.flushBuffer();
        } catch (IOException e) {
            exception2 = e;
        }

        if (exception1 != null) throw exception1;
        if (exception2 != null) throw exception2;
    }

    /**
     *
     * @return {@link GZipServletOutputStream}
     * @throws IOException - because working with {@link OutputStream}
     */
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (printWriter != null) {
            throw new IllegalStateException(
                    "PrintWriter obtained already - cannot get OutputStream");
        }
        if (gzipOutputStream == null) {
            gzipOutputStream = new GZipServletOutputStream(
                    getResponse().getOutputStream());
        }
        return gzipOutputStream;
    }

    /**
     *
     * @return {@link PrintWriter}
     * @throws IOException - because working with {@link OutputStream}
     */
    @Override
    public PrintWriter getWriter() throws IOException {
        if (printWriter == null && this.gzipOutputStream != null) {
            throw new IllegalStateException(
                    "OutputStream obtained already - cannot get PrintWriter");
        }
        if (printWriter == null) {
            gzipOutputStream = new GZipServletOutputStream(
                    getResponse().getOutputStream());
            printWriter = new PrintWriter(new OutputStreamWriter(
                    gzipOutputStream, getResponse().getCharacterEncoding()));
        }
        return printWriter;
    }

    /**
     * ignore, since content length of zipped content
     * does not match content length of unzipped content.
     *
     * @param len - length
     */
    @Override
    public void setContentLength(int len) {
    }
}