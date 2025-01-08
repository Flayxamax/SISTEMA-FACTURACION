export interface Environment {
  production: boolean;
  API_URL: string;
}

export const environment: Environment = {
  production: false,
  API_URL: 'http://127.0.0.1:8080',
};
