import * as React from 'react';
import { ChangeEvent } from 'react';
import TextField from '@mui/material/TextField';
import IPlanet from "../../types/planet.type";

interface PlanetRowProps {
  planet: IPlanet;
  onChangePlanet: (planet: IPlanet) => void;
  onRemovePlanet: () => void;
}

export default function PlanetRow({ planet, onChangePlanet, onRemovePlanet }: PlanetRowProps) {
  const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    onChangePlanet({
      ...planet,
      [name]: value
    })
  };

  return (
    <tr style={{ width: '100%' }}>
      <td>
        <TextField value={planet.planetName} label="Planet" name="planetName" InputLabelProps={{ shrink: true }} onChange={handleChange} />
      </td>
      <td>
        <TextField value={planet.x} label="X" name="x"
                   style={{ width: 60 }}
                   InputLabelProps={{ shrink: true }}
                   onChange={handleChange}
                   onFocus={event => {
                     event.target.select();
                   }}
        />
        <TextField value={planet.y} label="Y" name="y"
                   style={{ width: 60 }}
                   InputLabelProps={{ shrink: true }}
                   onChange={handleChange}
                   onFocus={event => {
                     event.target.select();
                   }}
        />
        <TextField value={planet.z} label="Z" name="z"
                   style={{ width: 60 }}
                   InputLabelProps={{ shrink: true }}
                   onChange={handleChange}

        />
      </td>
      <td>
        <button className="btn btn-secondary" onClick={onRemovePlanet}> - </button>&nbsp;
      </td>
    </tr>
  );
}