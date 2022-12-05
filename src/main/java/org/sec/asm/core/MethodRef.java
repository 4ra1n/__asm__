package org.sec.asm.core;

import java.util.Objects;

final class MethodRef {

    final String name;
    final String desc;

    MethodRef(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MethodRef)) {
            return false;
        }

        MethodRef that = (MethodRef) o;

        if (!Objects.equals(name, that.name)) {
            return false;
        }
        return Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        return result;
    }
}
