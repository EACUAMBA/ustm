package com.eacuamba.puzzle;

public class StringBufferHelper {
    private StringBuffer stringBuffer = new StringBuffer();
    public synchronized void addTextToBuffer() {
        addTextToBuffer("");
    }

    public synchronized void addTextToBuffer(String text) {
        stringBuffer.append(text).append("\n");
    }

    public synchronized void addTextToBufferFormatted(String text, Object... objects) {
        stringBuffer.append(String.format(text, objects));
    }

    public String getTextBuffered() {
        return stringBuffer.toString();
    }

    public void cleanBuffer() {
        stringBuffer.delete(0, stringBuffer.length() - 1);
    }

    public StringBuffer getStringBuffer(){
        return this.stringBuffer;
    }
}
