import axios from 'axios';
import type {Asset, SensorReading} from '../types';

const API_BASE_URL = 'http://localhost:8080/api';

export const getAssets = () =>
    axios.get<Asset[]>(`${API_BASE_URL}/assets`);

export const getAssetById = (id: string) =>
    axios.get<Asset>(`${API_BASE_URL}/assets/${id}`);

export const getLatestReading = (assetId: string) =>
    axios.get<SensorReading>(`${API_BASE_URL}/assets/${assetId}/readings/latest`);

export const createAsset = (assetData: { name: string; type: string; status: string }) =>
    axios.post<Asset>(`${API_BASE_URL}/assets`, assetData);