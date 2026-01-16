package org.diet.Util;

import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.metadata.CustomDataField;
import com.palmergames.bukkit.towny.object.metadata.IntegerDataField;

public final class WarTokenUtil {

    public static final String KEY = "saw_nation_war_tokens";
    public static final String LABEL = "War Tokens";

    // Template field (never attached directly)
    private static final IntegerDataField TEMPLATE =
            new IntegerDataField(KEY, 0, LABEL);

    private WarTokenUtil() {}

    private static IntegerDataField getOrCreate(Nation nation) {
        if (!nation.hasMeta(KEY)) {
            nation.addMetaData(TEMPLATE.clone());
        }

        CustomDataField cdf = nation.getMetadata(KEY);
        return (cdf instanceof IntegerDataField)
                ? (IntegerDataField) cdf
                : null;
    }

    public static int getTokens(Nation nation) {
        IntegerDataField field = getOrCreate(nation);
        return field != null ? field.getValue() : 0;
    }

    public static void setTokens(Nation nation, int value) {
        IntegerDataField field = getOrCreate(nation);
        if (field != null) {
            field.setValue(value);
        }
    }

    public static void addToken(Nation nation) {
        setTokens(nation, getTokens(nation) + 1);
    }
}
