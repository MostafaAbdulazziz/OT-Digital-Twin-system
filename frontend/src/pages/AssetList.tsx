import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { getAssets } from '../services/api';
import type {Asset} from '../types';
import AddAssetModal from '../components/AddAssetModal';

export default function AssetList() {
    const [assets, setAssets] = useState<Asset[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);
    const [isModalOpen, setIsModalOpen] = useState<boolean>(false);

    const fetchAssets = async () => {
        try {
            const response = await getAssets();
            setAssets(response.data);
            setError(null);
        } catch (err) {
            setError('Failed to connect to the Digital Twin backend.');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchAssets();
        const intervalId = setInterval(fetchAssets, 5000);
        return () => clearInterval(intervalId);
    }, []);

    const getStatusBadge = (status: string) => {
        let color = '#6b7280';
        let bgColor = '#f3f4f6';

        if (status === 'RUNNING') {
            color = '#166534';
            bgColor = '#dcfce7';
        } else if (status === 'ALARM') {
            color = '#991b1b';
            bgColor = '#fee2e2';
        }

        return (
            <span className="status-badge" style={{
                backgroundColor: bgColor,
                color: color,
                padding: '4px 12px',
                borderRadius: '9999px',
                fontWeight: 'bold',
                fontSize: '0.85rem'
            }}>
        {status}
      </span>
        );
    };

    if (loading) return <div className="container">Loading assets...</div>;
    if (error) return <div className="container error">{error}</div>;

    return (
        <div className="container">
            <header className="header">
                <h1>OT Digital Twin Dashboard</h1>
                <div className="header-actions">
                    <button onClick={() => setIsModalOpen(true)} className="btn-primary">
                        + Add Asset
                    </button>
                    <button onClick={fetchAssets} className="btn-refresh">Refresh</button>
                </div>
            </header>

            <div className="card">
                <table className="asset-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Asset Name</th>
                        <th>Type</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {assets.map((asset) => (
                        <tr key={asset.id}>
                            <td>{asset.id}</td>
                            <td style={{ fontWeight: '500' }}>{asset.name}</td>
                            <td>{asset.type}</td>
                            <td>{getStatusBadge(asset.status)}</td>
                            <td>
                                <Link to={`/assets/${asset.id}`} className="link-btn">
                                    View Telemetry
                                </Link>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>

            <AddAssetModal
                isOpen={isModalOpen}
                onClose={() => setIsModalOpen(false)}
                onSuccess={fetchAssets}
            />
        </div>
    );
}