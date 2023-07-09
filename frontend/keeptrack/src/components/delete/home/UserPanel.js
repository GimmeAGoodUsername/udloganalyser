import React, { Component } from 'react'
import { Icon, Label, Menu, Table, TableBody, Item, Button } from 'semantic-ui-react'
import RaceDropdown from './RaceDropdown'
import RoleDropdown from './RoleDropdown'
import PlanetDisplay from './PlanetDisplay'

class UserPanel extends Component {
    static userPanel = null;
    state = {
        user: null,
        userPanel: null
    }
    constructor(props){
        this.state.user = props.user
        if (user.user === null) {
            userPanel = <Item>No User</Item>
        }else {
            userPanel = <div><Table celled>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>Username</Table.HeaderCell>
                        <Table.HeaderCell>Race</Table.HeaderCell>
                        <Table.HeaderCell>Role</Table.HeaderCell>
                        <Table.HeaderCell>Planets</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>
                <TableBody>
                    <Table.Cell>
                        <Label>{this.state.user.name}</Label>
                    </Table.Cell>
                    <Table.Cell>
                        <RaceDropdown race={this.state.race} />
                    </Table.Cell>
                    <Table.Cell>
                        <RoleDropdown role={this.state.role} />
                    </Table.Cell>
                    <Table.Cell>
                        {this.state.planets.map(
                            planet => <PlanetDisplay planet={planet} />)}
                    </Table.Cell>
                </TableBody>
            </Table>
                <Button animated='vertical'>
                    <Button.Content hidden>Edit</Button.Content>
                    <Button.Content visible>
                        <Icon name='edit' />
                    </Button.Content>
                </Button>
            </div>;
        }

    }

   
}
export default UserPanel