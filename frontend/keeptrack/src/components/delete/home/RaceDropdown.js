import React from 'react'
import { Dropdown, Select } from 'semantic-ui-react'

function RaceDropdown(race)  {
    const selectedRace = race.race || 'mensch';
    const raceOptions = [
        { key: 'mensch', text: 'Mensch', value: 'mensch' },
        { key: 'mosoraner', text: 'Mosoraner', value: 'mosoraner' },
        { key: 'morricaner', text: 'Morricaner', value: 'morricaner' },
        { key: 'ozoid', text: 'Ozoid', value: 'ozoid' },
        { key: 'zuup', text: 'Zuup', value: 'zuup' },
        { key: 'wegoner', text: 'Wegoner', value: 'wegoner' },
        { key: 'plentrop', text: 'Plentrop', value: 'plentrop' },
        { key: 'jamazoid', text: 'Jamazoid', value: 'jamazoid' },
        { key: 'magumer', text: 'Magumer', value: 'magumer' }
    ]

    return (
    
        <Dropdown
            fluid
            selection
            value={selectedRace}
            options={raceOptions}
            
        />
    )
}

export default RaceDropdown