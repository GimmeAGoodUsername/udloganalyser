import * as React from 'react';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
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
  updatePlanet: (arg: IPlanet) => void
  planets: IPlanet[],
  selectedPlanet?: IPlanet
}

export default function PlanetPicker({ planets, selectedPlanet, updatePlanet }: PlanetPickerProps) {
  const onPlanetChange = (event: SelectChangeEvent) => {
    const value = event.target.value;
    const planet = planets.find(p => p.planetName === value);

    if (planet) {
      updatePlanet(planet)
    }
  };

  return (
    <div>{planets.length > 0 && (
      <FormControl sx={{ my: 1, width: 300 }}>
        <InputLabel id="demo-multiple-name-label">Name</InputLabel>
        <Select
          labelId="demo-multiple-name-label"
          id="demo-multiple-name"
          defaultValue={selectedPlanet?.planetName || ''}
          value={selectedPlanet?.planetName || ''}
          onChange={onPlanetChange}
          input={<OutlinedInput label="Planet"/>}
          MenuProps={MenuProps}
        >
          {planets.map((planet) => (
            <MenuItem
              key={planet.planetName}
              value={planet.planetName}>
              {planet.planetName}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
    )}</div>


  );
}