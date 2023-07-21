import SrOrder from "./order.type";
import ISrUser from "./sruser.type";

export default interface IPlanet{
    id?:any | null,
    planetName?: string,
    x?: number,
    y?: number,
    z?: number,
    srUser: ISrUser,
    orders: SrOrder[]
}