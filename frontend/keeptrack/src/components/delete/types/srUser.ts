import React from 'react'



export interface SrUser{
    id?: number;
    name?: string;
    race?: Race;
    role?: Role;
}

export enum Race{
    mensch,mosoraner,morricaner,ozoid,zuup,wegoner,plentrop,jamazoid,magumer
} 

export enum Role{
    trader,warlord,freelancer
}

export interface Planet{
    id?: number;
    planetName?: string;
    x?: number;
    y?: number;
    z?: number;
    srUser?: SrUser;
}

export default SrUser;
