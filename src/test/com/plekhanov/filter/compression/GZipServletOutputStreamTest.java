package com.plekhanov.filter.compression;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;

public class GZipServletOutputStreamTest {
    @Mock
    private OutputStream outputStream;
    private GZipServletOutputStream gZipServletOutputStream;
    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        gZipServletOutputStream = new GZipServletOutputStream(outputStream);
    }

    @Test
    public void shouldWriteByteArray() throws IOException {
        //given
        byte[] bytes = new byte[]{'a'};
        //when
        gZipServletOutputStream.write(bytes);
        //then
        gZipServletOutputStream.close();
    }
    @Test
    public void shouldWriteByteArrayWithOffset() throws IOException {
        //given
        char expected = 'a';
        byte[] bytes = new byte[]{(byte) expected};
        int offset = 0;
        int length = 1;
        //when
        gZipServletOutputStream.write(bytes,offset, length);
        //then
        gZipServletOutputStream.flush();
        gZipServletOutputStream.close();
    }
    @Test
    public void shouldWriteInt() throws IOException {
        //given
        int number = 1;
        //when
        gZipServletOutputStream.write(number);
        //then
        gZipServletOutputStream.close();
    }
}