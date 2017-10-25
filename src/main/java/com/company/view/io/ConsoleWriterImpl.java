package com.company.view.io;

import java.io.PrintStream;

public class ConsoleWriterImpl implements Writer {
    private PrintStream stream = System.out;

    public PrintStream getStream() {
        return stream;
    }

    public void setStream(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void printLine(Object str) {
        stream.println(str);
    }
}
