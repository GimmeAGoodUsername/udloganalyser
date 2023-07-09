import React from 'react'
import { Input } from 'semantic-ui-react'


function PlanetDisplay(planet) {
    const x = planet.planet.x;
    const y = planet.planet.y;
    const z = planet.planet.z;
    const xRef = React.useRef();
    const yRef = React.useRef();
    const zRef = React.useRef();
    const planetNameRef = React.useRef();
    const name = planet.planet.planetName

    console.log(planet.planet)
    return (
        <div class='ui input'>
            <Input type='text' value={name} label={{ basic: true, content: 'Name' }} labelPosition='left' ref={planetNameRef} />
            <Input type='number' value={x}  label={{ basic: true, content: 'x' }} labelPosition='left' ref={xRef}/>
            <Input type='number' value={y} label={{ basic: true, content: 'y' }} labelPosition='left' ref={yRef}/>
            <Input type='number' value={z} label={{ basic: true, content: 'z' }} labelPosition='left' ref={zRef}/>

        </div>
    )

}

export default PlanetDisplay