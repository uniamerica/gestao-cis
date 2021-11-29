import { Fragment } from "react";
import CustomizedTables from "../../Components/GenericUserTable";
import Container from '@mui/material/Container'

export default function Home() {

    return (
        <Fragment>
            <Container maxWidth="lg">
                <CustomizedTables></CustomizedTables>
            </Container>
        </Fragment>
    );

}