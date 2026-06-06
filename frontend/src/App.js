import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import CadastroPage from "./pages/CadastroPage";
import TarefasPage from "./pages/TarefasPage";
import HistoricoPage from "./pages/HistoricoPage";
import UsuarioPage from "./pages/UsuarioPage";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<CadastroPage />} />
                <Route path="/tarefas" element={<TarefasPage />} />
                <Route path="/historico" element={<HistoricoPage />} />
                <Route path="/usuarios" element={<UsuarioPage />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
