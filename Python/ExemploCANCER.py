# Importar bibliotecas necessárias
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import classification_report, accuracy_score, confusion_matrix, roc_curve, auc
from sklearn.datasets import load_breast_cancer
import matplotlib.pyplot as plt
import seaborn as sns

# Carregar o conjunto de dados do câncer de mama
bc = load_breast_cancer()
data = pd.DataFrame(data=bc.data, columns=bc.feature_names)
data['target'] = bc.target

# Mostrar as primeiras linhas do DataFrame para entender a estrutura dos dados
print(data.head())

# Selecionar características (X) e rótulos (y)
X = data.drop('target', axis=1)
y = data['target']

# Dividir os dados em conjuntos de treinamento e teste
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.99, random_state=42)

# Criar e treinar modelo de regressão logística
model = LogisticRegression(max_iter=10000, random_state=42)
model.fit(X_train, y_train)

# Fazer previsões no conjunto de teste
y_pred = model.predict(X_test)

# Avaliar modelo
print('Acurácia:', accuracy_score(y_test, y_pred))
print('Relatório de Classificação:')
print(classification_report(y_test, y_pred))

# Gerar a matriz de confusão
cm = confusion_matrix(y_test, y_pred)
plt.figure(figsize=(8, 6))
sns.heatmap(cm, annot=True, fmt='d', cmap='Blues', xticklabels=bc.target_names, yticklabels=bc.target_names)
plt.xlabel('Predição')
plt.ylabel('Verdadeiro')
plt.title('Matriz de Confusão')
plt.show()

# Gerar a curva ROC
fpr, tpr, _ = roc_curve(y_test, model.predict_proba(X_test)[:, 1])
roc_auc = auc(fpr, tpr)
plt.figure(figsize=(8, 6))
plt.plot(fpr, tpr, color='darkorange', lw=2, label=f'Curva ROC (area = {roc_auc:.2f})')
plt.plot([0, 1], [0, 1], color='navy', lw=2, linestyle='--')
plt.xlim([0.0, 1.05])
plt.ylim([0.0, 1.05])
plt.yticks([i/20.0 for i in range(0, 22)])
plt.xticks([i/20.0 for i in range(0, 21)])
plt.xlabel('Taxa de Falso Positivo')
plt.ylabel('Taxa de Verdadeiro Positivo')
plt.title('Curva ROC')
plt.legend(loc='lower right')
plt.show()

# Dicionário de tradução de colunas (exemplo)
translation_dict = {
    'mean radius': 'Raio médio',
    'mean texture': 'Textura média',
    'mean perimeter': 'Perímetro médio',
    'mean area': 'Área média',
    'mean smoothness': 'Suavidade média',
    'mean compactness': 'Compacidade média',
    'mean concavity': 'Concavidade média',
    'mean concave points': 'Pontos côncavos médios',
    'mean symmetry': 'Simetria média',
    'mean fractal dimension': 'Dimensão fractal média',
    'radius error': 'Erro do raio',
    'texture error': 'Erro da textura',
    'perimeter error': 'Erro do perímetro',
    'area error': 'Erro da área',
    'smoothness error': 'Erro da suavidade',
    'compactness error': 'Erro da compacidade',
    'concavity error': 'Erro da concavidade',
    'concave points error': 'Erro dos pontos côncavos',
    'symmetry error': 'Erro da simetria',
    'fractal dimension error': 'Erro da dimensão fractal',
    'worst radius': 'Pior raio',
    'worst texture': 'Pior textura',
    'worst perimeter': 'Pior perímetro',
    'worst area': 'Pior área',
    'worst smoothness': 'Pior suavidade',
    'worst compactness': 'Pior compacidade',
    'worst concavity': 'Pior concavidade',
    'worst concave points': 'Piores pontos côncavos',
    'worst symmetry': 'Pior simetria',
    'worst fractal dimension': 'Pior dimensão fractal'
}

# Renomear as colunas do DataFrame
data.rename(columns=translation_dict, inplace=True)

# Refazer a seleção das características (X) e rótulos (y)
X = data.drop('target', axis=1)
y = data['target']

# Repetir o processo de treinamento e geração do gráfico
# Treinar o modelo novamente após renomear as colunas
model = LogisticRegression(max_iter=10000, random_state=42)
model.fit(X_train, y_train)

# Gerar o gráfico de importância das características com nomes em português
coefficients = model.coef_[0]
features = X.columns

importance_df = pd.DataFrame({'Feature': features, 'Coefficient': coefficients})
importance_df['abs_coefficient'] = importance_df['Coefficient'].abs()
importance_df = importance_df.sort_values(by='abs_coefficient', ascending=False)

plt.figure(figsize=(10, 8))
sns.barplot(x='Coefficient', y='Feature', data=importance_df, palette='viridis')
plt.title('Importância das Características')
plt.xlabel('Coeficiente')
plt.ylabel('Característica') 

# Ajustar os limites do eixo X para uma melhor escala
plt.xlim(-max(importance_df['abs_coefficient']), max(importance_df['abs_coefficient']))

plt.show()

