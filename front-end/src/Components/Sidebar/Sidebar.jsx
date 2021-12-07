import React, {useEffect} from "react";
import Box from "@mui/material/Box";
import Drawer from "@mui/material/Drawer";
import Button from "@mui/material/Button";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import LocalHospitalIcon from "@mui/icons-material/LocalHospital";
import MenuIcon from "@mui/icons-material/Menu";
import FactCheckIcon from '@mui/icons-material/FactCheck';
import AssignmentIndIcon from "@mui/icons-material/AssignmentInd";
import DateRangeIcon from "@mui/icons-material/DateRange";
import MeetingRoomIcon from "@mui/icons-material/MeetingRoom";
import Logo from "../../assets/images/logo.png";
import Avatar from "@mui/material/Avatar";
import LogoutIcon from "@mui/icons-material/Logout";
import Typography from "@mui/material/Typography";
import { sideBarStyle } from "./style.js";
import { AuthContext } from "./../../Contexts/authContext";
import Cookies from "js-cookie";
import { useNavigate } from "react-router";

const sideBarItems = [
  {
    text: "Agendamentos",
    icon: <DateRangeIcon />,
    route: "/home",
  },
  {
    text: "Pacientes",
    icon: <AssignmentIndIcon />,
    route: "/admin/pacientes",
  },
  {
    text: "Profissionais",
    icon: <LocalHospitalIcon />,
    route: "/admin/profissionais",
  },
  {
    text: "Gerenciamento de Salas",
    icon: <MeetingRoomIcon />,
    route: "/admin/salas",
  },
  {
    text: "Agenda",
    icon: <FactCheckIcon />,
    route: "/professional/schedule",
  },
];

export default function Sidebar() {
  const [menuOpen, setMenuOpen] = React.useState(false);
  const { isAuth, user } = React.useContext(AuthContext);
  const navigate = useNavigate();

  function Logout() {
    Cookies.remove("cis.validator");
    window.location.replace("login");
  }

  function onClickButton(route) {
    setMenuOpen(false);
    navigate(route);
  }

  if (!isAuth) {
    return <></>;
  } else {
    return (
      <div>
        <React.Fragment>
          <Box sx={{ position: "fixed", left: "60px", top: "100px" }}>
            <Button variant="outlined" onClick={() => setMenuOpen(true)}>
              <MenuIcon />
              <ListItemText>Menu</ListItemText>
            </Button>
            <Drawer anchor="left" onOpen={() => setMenuOpen(true)} onClose={() => setMenuOpen(false)} open={menuOpen}>
              <Box sx={sideBarStyle} role="presentation">
                <List>
                  <Box sx={{ width: "100%", paddingTop: '8%', marginTop: '-12%', height: '20%', textAlign: "center", backgroundColor: '#fff' }}>
                    <img src={Logo} width={250} />
                  </Box>
                <Box sx={{width: "100%",
                height: '25%',
                display: "flex", 
                justifyItems: 'center',
                alignItems: 'center',
                flexDirection: "column", 
                alignItems: "center", 
                margin: "24px 0", 
                backgroundColor: '#00000025', 
                marginTop: 0,
                paddingTop: '5%', 
                paddingBottom: '5%'}}>
                  <Avatar sx={{ width: 56, height: 56 }}>V</Avatar>
                  <Typography
                    variant="h5"
                    color="#FFFF"
                    sx={{ marginTop: "15px" }}
                  >
                    Olá {user.sub}
                  </Typography>
                </Box>
                  {sideBarItems.map(({ text, icon, route }) => (
                    <ListItem button key={text} sx={{ marginTop: "12px" }} onClick={() => onClickButton(route)} >
                      <ListItemIcon sx={{ color: "white" }}>
                        {icon}
                      </ListItemIcon>
                      <ListItemText primary={text} sx={{ color: "white" }} />
                    </ListItem>
                  ))}
                </List>
                <List>
                  <ListItem button sx={{ marginTop: "12px" }} onClick={() => window.confirm("Deseja realizar logout do sistema?") ? Logout() : ""}>
                    <ListItemIcon sx={{ color: "#FFFF" }}>
                      <LogoutIcon />
                    </ListItemIcon>
                    <ListItemText sx={{ color: "#FFFF" }}>Logout</ListItemText>
                  </ListItem>
                </List>
              </Box>
            </Drawer>
          </Box>
        </React.Fragment>
      </div>
    );
  }
}
