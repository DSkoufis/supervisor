package com.supervisor.util.constant;

import java.util.HashMap;
import java.util.Map;

public final class UniqueKeyConstraintMapper {
    public final static String UNIQUE_PRODUCT_NAME = "product_unique_name_key";

    public static String getCode(String constraintName) {
        return UniqueKeyMapper.findByConstraintName(constraintName);
    }

    private enum UniqueKeyMapper {
        UNIQUE_PRODUCT_NAME_KEY(UNIQUE_PRODUCT_NAME, "product.Product.name.unique");

        private String constraintName;
        private String messageCode;

        private static final Map<String, UniqueKeyMapper> constraintLookup = new HashMap<>();
        static {
            for (UniqueKeyMapper uniqueKey : UniqueKeyMapper.values()) {
                constraintLookup.put(uniqueKey.constraintName, uniqueKey);
            }
        }

        UniqueKeyMapper(String constraintName, String messageCode) {
            this.constraintName = constraintName;
            this.messageCode = messageCode;
        }

        private static String findByConstraintName(String constraintName) {
            UniqueKeyMapper mapper = constraintLookup.get(constraintName);
            if (mapper != null) {
                return mapper.messageCode;
            } else {
                throw new IllegalArgumentException("Couldn't find the specified constraint in the current list");
            }
        }
    }
}
