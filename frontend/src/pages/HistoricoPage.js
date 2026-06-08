import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

function HistoricoPage() {

    const [historicos, setHistoricos] = useState([]);

    useEffect(() => {
        carregarHistorico();
    }, []);

    const carregarHistorico = async () => {

        const response = await axios.get(
            "http://localhost:8090/historico"
        );

        setHistoricos(response.data);
    };

    return (
        <div className="container">

            <div className="card">

                <h1>Histórico de Usuários</h1>

                <ul>
                    {historicos.map((historico) => (
                        <li key={historico.id}>
                            {historico.usuarioId}
                            {" | "}
                            {historico.nomeUsuario}
                            {" | "}
                            {historico.acao}
                            {" | "}
                            {historico.dataHora}
                        </li>
                    ))}
                </ul>

                <Link to="/">
                    <button>
                        Voltar
                    </button>
                </Link>

            </div>

        </div>
    );
}

export default HistoricoPage;