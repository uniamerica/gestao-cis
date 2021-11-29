import * as React from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: "#1CA78C",
    color: theme.palette.common.white,
    fontSize: 18,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
    
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  '&:nth-of-type(odd)': {
    backgroundColor:"#dcdcdc",
    
  },

}));

function createData(clinic, identification, category, availability) {
  return { clinic, identification, category, availability };
}

const rows = [
  createData('Frozen ', 1, "locura", true),   //padronizar as inf
  createData('Elsa ', 2, "tortura", false),
  createData('Anna ', 3, "masoquismmo", true),
  createData('Olaf ', 4, "ahhh", false),
  createData('Rapariga ', 5, "putero", true),
];

export default function CustomizedTables() {
  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell>Nomes</StyledTableCell>
            <StyledTableCell align="right">Sala</StyledTableCell>
            <StyledTableCell align="right">Especialidade</StyledTableCell>
            <StyledTableCell align="right">Disponibilidade</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <StyledTableRow key={row.clinic}>
              <StyledTableCell component="th" scope="row">
                {row.clinic}
              </StyledTableCell>
              <StyledTableCell align="right">{row.identification}</StyledTableCell>
              <StyledTableCell align="right">{row.category}</StyledTableCell>
              <StyledTableCell align="right">{row.availability ? "sim" : "não"}</StyledTableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}