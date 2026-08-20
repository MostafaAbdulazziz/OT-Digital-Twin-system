import { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import { getAssetById, getLatestReading } from '../services/api';
import type {Asset, SensorReading} from '../types';

export default function AssetDetails() {
    const { id } = useParams<{ id: string }>();
    const [asset, setAsset] = useState<Asset | null>(null);
    const [reading, setReading] = useState<SensorReading | null>(null);
    const [loading, setLoading] = useState<boolean>(true);

    useEffect(() => {
        if (!id) return;

        const fetchTelemetry = async () => {
            try {
                const [assetRes, readingRes] = await Promise.all([
                    getAssetById(id),
                    getLatestReading(id)
                ]);
                setAsset(assetRes.data);

                if (readingRes.status === 200 && readingRes.data) {
                    setReading(readingRes.data);
                }
            } catch (err) {
                console.error("Failed to fetch asset telemetry", err);
            } finally {
                setLoading(false);
            }
        };

        fetchTelemetry();
        const intervalId = setInterval(fetchTelemetry, 5000);
        return () => clearInterval(intervalId);
    }, [id]);

    if (loading) return <div className="container">Loading telemetry...</div>;
    if (!asset) return <div className="container error">Asset not found.</div>;

    return (
        <div className="container">
            <Link to="/" className="back-link">&larr; Back to Dashboard</Link>

            <div className="card details-card">
                <h2>{asset.name} Overview</h2>
                <div className="meta-grid">
                    <div className="meta-item">
                        <span className="label">Type</span>
                        <span className="value">{asset.type}</span>
                    </div>
                    <div className="meta-item">
                        <span className="label">Current Status</span>
                        <span className="value">{asset.status}</span>
                    </div>
                </div>

                <div className="sensor-panel">
                    <h3>Live Telemetry</h3>
                    {reading ? (
                        <div className="reading-grid">
                            <div className="reading-box">
                                <span className="reading-label">Temperature</span>
                                <span className="reading-value temp">
                  {reading.temperature.toFixed(2)} &deg;C
                </span>
                            </div>
                            <div className="reading-box">
                                <span className="reading-label">Pressure</span>
                                <span className="reading-value pressure">
                  {reading.pressure.toFixed(2)} Bar
                </span>
                            </div>
                            <div className="reading-time">
                                Last updated: {new Date(reading.timestamp).toLocaleTimeString()}
                            </div>
                        </div>
                    ) : (
                        <p className="no-data">Awaiting sensor data...</p>
                    )}
                </div>
            </div>
        </div>
    );
}