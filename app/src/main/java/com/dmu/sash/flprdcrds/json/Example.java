package com.dmu.sash.flprdcrds.json;

import java.util.List;

public class Example {
    /**
     * registers : ["figurative"]
     * text : life had started dealing him aces again
     */

    private String text;
    private List<String> registers;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getRegisters() {
        return registers;
    }

    public void setRegisters(List<String> registers) {
        this.registers = registers;
    }
}
