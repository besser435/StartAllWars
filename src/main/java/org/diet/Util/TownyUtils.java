package org.diet.Util;

import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;


public class TownyUtils {
    // Generic methods that will be useful in lots of places to reduce repeat code.
    // TODO: kind of a shitfuck class, some of these methods arent useful. Add new ones later when cleaning the code.


    /**
     * @return true if the resident exists and has a town.
     */
    public boolean isValidResidentWithTown(Resident resident) {
        return resident != null && resident.hasTown();
    }

    /**
     * @return true if the resident exists and has a nation.
     */
    public boolean isValidResidentWithTownAndNation(Resident resident) {
        return resident != null && resident.hasTown() && resident.hasNation();
    }

    /**
     * @return true if the resident exists and is the leader of the provided nation.
     */
    public boolean isNationLeader(Resident resident, Nation nation) {
        return resident != null && nation.getKing().equals(resident);
    }
}
