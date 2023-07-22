import * as React from 'react';
import { ChangeEvent, useState } from 'react';
import TextField from '@mui/material/TextField';

export interface Planet {
  planetName?: string;
  x?: number;
  y?: number;
  z?: number;
}

interface PlanetRowProps {
  planet: Planet;
  onSavePlanet: (planet: Planet) => void;
  onRemovePlanet: () => void;
}

export default function PlanetRow(props: PlanetRowProps) {
  const [ planet, setPlanet ] = useState<Planet>(props.planet)
  const [ isEditing, setIsEditing ] = useState<boolean>(!props.planet.planetName)

  const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setPlanet({
      ...planet,
      [name]: value
    })
  };

  const onSave = () => {
    setIsEditing(false)
    props.onSavePlanet(planet)
  }

  const onEdit = () => {
    setIsEditing(true)
  }

  const onDelete = () => {
    const doDelete = window.confirm(`Möchtest du den Planeten ${planet.planetName || 'ohne Namen'} wirklich löschen?`)
    if (doDelete) {
      props.onRemovePlanet()
    }
  }

  return (
    <tr style={{ width: '100%' }}>
    { isEditing ?
      <>
        <td>
          <TextField value={planet.planetName} label="Planet" name="planetName" onChange={handleChange}/>
        </td>
        <td>
          <TextField value={planet.x} label="X" name="x" style={{ width: 60 }} onChange={handleChange} />
          <TextField value={planet.y} label="Y" name="y" style={{ width: 60 }} onChange={handleChange} />
          <TextField value={planet.z} label="Z" name="z" style={{ width: 60 }} onChange={handleChange} />
        </td>
        <td>
          <button className="btn btn-secondary" onClick={onSave}>Save</button>&nbsp;
        </td>
      </>
        :
      <>
        <td>{planet.planetName}</td>
        <td>{planet.x}-{planet.y}-{planet.z}</td>
        <td>
          <button className="btn btn-secondary" onClick={onEdit}>Edit</button>&nbsp;
          <button className="btn btn-secondary" onClick={onDelete}>Del</button>
        </td>
      </>
    }
    </tr>
  );
}