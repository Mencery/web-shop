package com.plekhanov.filter.compression;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * {@link ServletOutputStream} for gzip
 */
class GZipServletOutputStream extends ServletOutputStream {
    private GZIPOutputStream gzipOutputStream;

    /**
     *
     * @param output - {@link OutputStream}
     * @throws IOException - throws if file not found
     */
    public GZipServletOutputStream(OutputStream output) throws IOException {
        super();
        gzipOutputStream = new GZIPOutputStream(output);
    }

    /**
     * closes stream
     * @throws IOException- throws if  not found
     */
    @Override
    public void close() throws IOException {
        gzipOutputStream.close();
    }

    /**
     * flushes stream
     * @throws IOException - throws if  not found
     */
    @Override
    public void flush() throws IOException {
        gzipOutputStream.flush();
    }

    /**
     * writes bytes in stream
     * @param b - array of byte
     * @throws IOException - throws if cannot write
     */
    @Override
    public void write(byte[] b) throws IOException {
        gzipOutputStream.write(b);
    }
    /**
     * writes bytes in stream with length and offset
     * @param b - array of byte
     * @throws IOException - throws if cannot write
     */
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        gzipOutputStream.write(b, off, len);
    }
    /**
     * writes integer in stream
     * @param b - int value
     * @throws IOException - throws if cannot write
     */
    @Override
    public void write(int b) throws IOException {
        gzipOutputStream.write(b);
    }
}