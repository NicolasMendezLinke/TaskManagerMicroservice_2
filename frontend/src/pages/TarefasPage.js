import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../App.css";

function TarefasPage() {

    const navigate = useNavigate();

    const [titulo, setTitulo] = useState("");
    const [descricao, setDescricao] = useState("");
    const [usuarioId, setUsuarioId] = useState("");

    const [tarefas, setTarefas] = useState([]);

    const carregarTarefas = async () => {

        const response = await axios.get(
            "http://localhost:8090/tarefas"
        );

        setTarefas(response.data);
    };

    const cadastrarTarefa = async () => {

        await axios.post(
            "http://localhost:8090/tarefas",
            {
                titulo,
                descricao,
                usuarioId: Number(usuarioId)
            }
        );

        setTitulo("");
        setDescricao("");
        setUsuarioId("");

        carregarTarefas();
    };

    return (
        <div className="container">

            <div className="card">

                <h1>Gerenciamento de Tarefas</h1>

                <input
                    placeholder="Título"
                    value={titulo}
                    onChange={(e) => setTitulo(e.target.value)}
                />

                <input
                    placeholder="Descrição"
                    value={descricao}
                    onChange={(e) => setDescricao(e.target.value)}
                />

                <input
                    placeholder="ID do Usuário"
                    value={usuarioId}
                    onChange={(e) => setUsuarioId(e.target.value)}
                />

                <button onClick={cadastrarTarefa}>
                    Criar Tarefa
                </button>

                <button onClick={carregarTarefas}>
                    Atualizar Lista
                </button>

                <button
                    onClick={() => navigate("/historico")}
                >
                    Ver Histórico
                </button>

                <button
                    onClick={() => navigate("/")}
                >
                    Página Inicial
                </button>

                <ul>
                    {tarefas.map((tarefa) => (
                        <li key={tarefa.id}>
                            {tarefa.id}
                            {" | "}
                            {tarefa.titulo}
                            {" | "}
                            {tarefa.status}
                            {" | "}
                            {tarefa.nomeUsuario
                                ? tarefa.nomeUsuario
                                : `Usuário ${tarefa.usuarioId}`}
                        </li>
                    ))}
                </ul>

            </div>

        </div>
    );
}

export default TarefasPage;