import { Fragment } from "react";
import { Box } from "@mui/material";
import BackgroundErro from "../../assets/images/404CIS.svg";

export default function Home() {

    return (
    <Fragment>
        <Box
          name="BackgroundErro"
          sx={{
            position: "fixed",
            zIndex: -1,
            maxHeight: "92vh",
            width: "100vw",
            display: "flex",
            justifyContent: "center",
          }}
        >
          <img src={BackgroundErro} alt="" />
        </Box>
    </Fragment>
    );
    
}