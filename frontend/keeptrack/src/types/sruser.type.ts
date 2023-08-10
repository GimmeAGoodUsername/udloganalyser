import SrOrder from "./order.type";
import IPlanet from "./planet.type";

export default interface ISrUser {
    id?: number | null,
    name?: string,
    race: Race,
    role: Role,
    planets?: IPlanet[],
    order?: SrOrder[],
    delivery?: SrOrder[],
    isUser: boolean,
    authorities: Authority

}

export enum Race {
    mensch = "mensch",
    mosoraner = "mosoraner",
    morricaner = "morricaner",
    ozoid = "ozoid",
    zuup = "zuup",
    wegoner = "wegoner",
    plentrop = "plentrop",
    jamazoid = "jamazoid",
    magumer = "magumer"
}

export enum Role {
    trader = "trader",
    warlord = "warlord",
    freelancer = "freelancer"
}

export enum Authority {
    NONE = "NONE",
    USER = "USER",
    ADMIN = "ADMIN"
}