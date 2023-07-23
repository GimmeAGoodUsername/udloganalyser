import * as React from 'react';
import FormControl from '@mui/material/FormControl';
import IPlanet from '../../types/planet.type';
import ISrUser from "../../types/sruser.type";
import PlanetRow from "./PlanetRow";

interface PlanetsProps {
  user: ISrUser,
  onRemovePlanet: (id: IPlanet['id'], i: number) => void,
  onAddPlanet: () => void
  onChangePlanet: (planet: IPlanet, i: number) => void
}

export default function PlanetList({ user, onRemovePlanet, onAddPlanet, onChangePlanet }: PlanetsProps) {
  return (
    <FormControl sx={{ my: 1, width: 470 }}>
      <table>
        <tbody>
        {user.planets?.map((p, i) => <PlanetRow
          key={i}
          planet={{planetName: p.planetName, x: p.x, y: p.y, z: p.z, id: p.id}}
          onChangePlanet={(planet) => onChangePlanet(planet, i)}
          onRemovePlanet={() => onRemovePlanet(p.id, i)}/>)
        }
        <tr>
          <th colSpan={3}>
            <button type="submit" className="btn btn-secondary" onClick={onAddPlanet} style={{ marginTop: 10 }}>
              + Neuer Planet
            </button>
          </th>
        </tr>
        </tbody>
      </table>
    </FormControl>
  );
}