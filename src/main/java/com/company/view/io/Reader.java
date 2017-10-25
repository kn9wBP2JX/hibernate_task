package com.company.view.io;

import java.io.IOException;

public interface Reader {
    String readString() throws IOException;

    Long readLong() throws IOException;

    Integer readInteger() throws IOException;
}
