import { Checkbox, Grid } from "@mui/material";
import { Formik, Form, Field } from "formik";
import React, { useState, useEffect } from "react";
import * as scanApi from "../services/scanner.service";
import * as AuthService from "../services/auth.service";


const Scanner: React.FC = () => {
    const [content, setContent] = useState<string[]>();
    const initialValues: {
        amount: number;
        scanrange: number;
        creep: boolean;
    } = {
        amount: 0,
        scanrange: 0,
        creep: false
    };

    const scanRange = (formValue: { amount: number; scanrange: number; creep: boolean }) => {
        scanApi.scan(formValue.amount, formValue.scanrange, formValue.creep).then(
            (response) => {
                setContent(response.data)
            },
            (error) => {
                AuthService.logout();
                const _content =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();

                setContent(_content);
            }
        );
    }
   
    return (
        <Grid container spacing={6}>
            <Grid item xs={6}>
                Scan Cluster
                <Formik
                    onSubmit={scanRange}
                    initialValues={initialValues}
                >
                    <Form>
                        <div className="form-group">
                            <label htmlFor="amount">Anzahl Coords</label>
                            <Field name="amount" type="number" className="form-control" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="scanrange">scanrange</label>
                            <Field name="scanrange" type="number" className="form-control" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="creep">Creeps</label>
                            <Field type="checkbox" name="creep" className="form-control" />
                        </div>

                        <div className="form-group">
                            <button type="submit" className="btn btn-primary btn-block">

                                <span>Ermitteln</span>
                            </button>
                        </div>



                    </Form>
                </Formik>
            </Grid>
            <Grid item xs={6}>
                <label>Klick die Checkbox für auto copy paste</label>
                {content?.map((coordinate) => (
                    <table>
                        <tr>
                            <td><label >{coordinate}</label></td>
                            <td><Checkbox onClick={() => { navigator.clipboard.writeText(coordinate) }} /></td>
                        </tr>
                    </table>
                )
                )}

            </Grid>
        </Grid>
    );
};

export default Scanner;