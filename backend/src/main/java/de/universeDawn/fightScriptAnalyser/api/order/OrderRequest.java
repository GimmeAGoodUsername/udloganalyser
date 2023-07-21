package de.universeDawn.fightscriptanalyser.api.order;

import de.universeDawn.fightscriptanalyser.user.SrOrder;

public record OrderRequest(SrOrder srOrder, String name) {
}
