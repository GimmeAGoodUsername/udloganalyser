import IPlanet from "./planet.type";
import ISrUser from "./sruser.type";

export default interface SrOrder{
    id?:any | null,
    titan: number,
    silicon: number,
    helium: number,
    food: number,
    water: number,
    alu: number,
    baux: number,
    uran: number,
    pluto: number,
    hydro: number,
    credits?: number,
    deliveryDate: Date,
    target: IPlanet,
    orderedBy: ISrUser,
    deliveryBoy?: ISrUser
}