import * as React from 'react';
import { useState } from 'react';
import FormControl from '@mui/material/FormControl';
import IPlanet from '../../types/planet.type';
import ISrUser from "../../types/sruser.type";
import PlanetRow, { Planet } from "./PlanetRow";

interface PlanetsProps {
  user: ISrUser,
  onUpdatePlanets: (planets: IPlanet[]) => void
}

export default function PlanetList({ user, onUpdatePlanets }: PlanetsProps) {
  const [ planets, setPlanets ] = useState<IPlanet[]>(user.planets || [])

  const onSavePlanet = (i: number, planet: Planet) => {
    planets[i] = {
      ...planets[i],
      id: undefined,
      planetName: planet.planetName,
      x: planet.x,
      y: planet.y,
      z: planet.z
    };
    setPlanets([...planets])
    onUpdatePlanets(planets)
  }

  const onRemovePlanet = (i: number) => {
    planets.splice(i, 1)
    setPlanets([...planets]);
    onUpdatePlanets(planets)
  }

  const addPlanet = () => {
    planets.push({
      srUser: user,
    })
    setPlanets([...planets])
  }


  return (
    <FormControl sx={{ m: 1, width: 550 }}>
      <table>
        <thead>
        <tr>
          <th style={{ width: '43%' }}>Name</th>
          <th style={{ width: '36%' }}>Koordinaten</th>
          <th></th>
        </tr>
        </thead>
        <tbody>
        {planets.map((p, i) => <PlanetRow
          key={i}
          planet={{planetName: p.planetName, x: p.x, y: p.y, z: p.z}}
          onSavePlanet={(planet) => onSavePlanet(i, planet)}
          onRemovePlanet={() => onRemovePlanet(i)}/>)
        }
        </tbody>
      </table>
      <button type="submit" className="btn btn-primary btn-block" onClick={() => addPlanet()}>
        Planet hinzufügen
      </button>
    </FormControl>


  );
}