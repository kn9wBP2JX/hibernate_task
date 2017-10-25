package com.company.view.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReaderImpl implements Reader {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public BufferedReader getBr() {
        return br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }

    @Override
    public String readString() throws IOException {
        return br.readLine();
    }

    @Override
    public Long readLong() throws IOException {
        return Long.parseLong(br.readLine());
    }

    @Override
    public Integer readInteger() throws IOException {
        return Integer.parseInt(br.readLine());
    }
}
