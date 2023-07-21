import SrOrder from "./order.type";
import IPlanet from "./planet.type";

export default interface ISrUser{
    id?:any | null,
    name?: string,
    race: Race,
    role: Role,
    planets?: IPlanet[],
    order?: SrOrder[],
    delivery?: SrOrder[],
    isUser: boolean
}

enum Race{
    mensch,mosoraner,morricaner,ozoid,zuup,wegoner,plentrop,jamazoid,magumer
}

enum Role{
    trader,warlord,freelancer
}