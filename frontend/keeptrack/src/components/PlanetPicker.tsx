import * as React from 'react';
import { Theme, useTheme } from '@mui/material/styles';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import ISrUser from '../types/sruser.type';
import IPlanet from '../types/planet.type';

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
interface PlanetPickerProps {
    user: ISrUser, 
    updatePlanet:(arg:IPlanet)=> void
}
export default function PlanetPicker({user, updatePlanet}:PlanetPickerProps) {
    const theme = useTheme();
    const [planet, setPlanet] = React.useState<IPlanet>();
    const [selectedPlanet, selectPlanet] = React.useState<string>();

    const handleChange = (event: SelectChangeEvent) => {
        const {
            target: { value },
        } = event;
        if(user && user.planets){
            user.planets.forEach(element => {
                if(element.planetName===value){
                    setPlanet(element);
                    updatePlanet(element);
                }
            });
        }
        
    };

    return (
    <div>{(user && user.planets) && (
        <FormControl sx={{ m: 1, width: 300 }}>
            <InputLabel id="demo-multiple-name-label">Name</InputLabel>
            <Select
                labelId="demo-multiple-name-label"
                id="demo-multiple-name"
                value={selectedPlanet}
                onChange={handleChange}
                input={<OutlinedInput label="Planet" />}
                MenuProps={MenuProps}
            >
                {user.planets.map((planet) => (
                    <MenuItem
                        key={planet.planetName}
                        value={planet.planetName} >
                        {planet.planetName}
                    </MenuItem>
                ))}
            </Select>
        </FormControl>
    )}</div>
        
    
  );
}