import numpy as np

# Corrigindo as dimensões e resolvendo o sistema da distribuição estacionária corretamente

# Matriz de transição P_4 para 4 estados
P_4 = np.array([
    [0.67, 0.33, 0, 0],
    [0.33, 0.34, 0.33, 0],
    [0, 0.33, 0.34, 0.33],
    [0, 0, 0.33, 0.67]
])

# Encontrar distribuição estacionária resolvendo pi * P = pi
# Matriz (P.T - I)
A_4 = P_4.T - np.eye(4)

# Adicionando a equação pi_1 + pi_2 + pi_3 + pi_4 = 1 (somatório das probabilidades é 1)
A_4 = np.vstack([A_4, [1, 1, 1, 1]])
b_4 = np.array([0, 0, 0, 0, 1])  # Adicionando a equação extra no sistema

# Resolvendo o sistema
pi_4 = np.linalg.lstsq(A_4, b_4, rcond=None)[0]

# Custos para os 4 estados (0, 1, 2, 3 falhas)
custos = np.array([0, 100, 200, 300])

# Calculando o gasto total esperado para 30 dias
gasto_total_4_estados = np.dot(pi_4, custos) * 30
pi_4, gasto_total_4_estados
