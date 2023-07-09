import React from 'react'
import { Dropdown, Select } from 'semantic-ui-react'

function RoleDropdown(role)  {
    const selectedRole = role.role || 'trader';
    const raceOptions = [
        { key: 'trader', text: 'Trader', value: 'trader' },
        { key: 'warlord', text: 'Warlord', value: 'warlord' },
        { key: 'freelancer', text: 'Freelancer', value: 'freelancer' }
        
    ]

    return (
    
        <Dropdown
            fluid
            selection
            value={selectedRole}
            options={raceOptions}
            
        />
    )
}

export default RoleDropdown