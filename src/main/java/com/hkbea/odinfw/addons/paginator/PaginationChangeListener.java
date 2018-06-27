package com.hkbea.odinfw.addons.paginator;

import java.io.Serializable;

public interface PaginationChangeListener extends Serializable {
    void changed(PaginationResource event);
}
