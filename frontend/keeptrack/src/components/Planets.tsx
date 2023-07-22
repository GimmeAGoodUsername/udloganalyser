import * as React from 'react';
import { Theme, useTheme } from '@mui/material/styles';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import ISrUser from '../types/sruser.type';
import IPlanet from '../types/planet.type';
import TextField from '@mui/material/TextField';

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
    PaperProps: {
        style: {
            maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
            width: 250,
        },
    },
};
interface PlanetsProbs {
    user: ISrUser,
    updatePlanet: (arg: IPlanet) => void
}
export default function Planets({ user, updatePlanet }: PlanetsProbs) {
    const theme = useTheme();
    const [planet, setPlanet] = React.useState<IPlanet>();
    const initialValues: {
        x: number, y: number, z: number, newPlanetName: string
    } = {
        x: 0, y: 0, z: 0, newPlanetName: "Planeten Name"
    }
    const handleChange = (event: SelectChangeEvent) => {
        const {
            target: { value },
        } = event;
        if (user && user.planets) {
            user.planets.forEach(element => {
                if (element.planetName === value) {
                    setPlanet(element);
                    updatePlanet(element);
                }
            });
        }

    };
    const addPlanet = (formValue: { x: number, y: number, z: number, planetName: string}) => {
        let  {
            planetName, x,y,z
        } = formValue;

    }

    return (
        <div>{(user && user.planets) && (
            <FormControl sx={{ m: 1, width: 300 }}>
                {user.planets.map((planet) => (
                    <div>{planet.planetName} {planet.x}-{planet.y}-{planet.z}</div>
                ))}
                <div>
                    <TextField
                        value={initialValues.newPlanetName}
                        id="outlined-required"
                        label="Planet" />
                    <TextField
                        value={initialValues.x}
                        id="outlined-required"
                        label="x"
                        type='number' />
                    <TextField
                        value={initialValues.y}
                        id="outlined-required"
                        label="y"
                        type='number' />
                    <TextField
                        value={initialValues.z}
                        id="outlined-required"
                        label="z"
                        type='number' />
                </div>
                <button type="submit" className="btn btn-primary btn-block" onClick={addPlanet}>
                    <span>Add</span>
                </button>
            </FormControl>
        )}</div>


    );
}