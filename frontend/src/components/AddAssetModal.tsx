import React, { useState } from 'react';
import { createAsset } from '../services/api';

interface AddAssetModalProps {
    isOpen: boolean;
    onClose: () => void;
    onSuccess: () => void; // Triggered to refresh the table
}

export default function AddAssetModal({ isOpen, onClose, onSuccess }: AddAssetModalProps) {
    const [name, setName] = useState('');
    const [type, setType] = useState('');
    const [status, setStatus] = useState('STOPPED');
    const [error, setError] = useState<string | null>(null);

    if (!isOpen) return null;

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError(null);

        // Front-end Validation
        if (!name.trim() || !type.trim()) {
            setError('Name and Type are required strings.');
            return;
        }

        if (!isNaN(Number(name)) || !isNaN(Number(type))) {
            setError('Name and Type must be valid text strings, not just numbers.');
            return;
        }

        try {
            await createAsset({ name, type, status });
            // Reset form on success
            setName('');
            setType('');
            setStatus('STOPPED');
            onSuccess(); // Tell the parent to refresh the list
            onClose();   // Close the modal
        } catch (err) {
            setError('Failed to create asset. Please try again.');
        }
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2>Add New Asset</h2>
                {error && <div className="error-message">{error}</div>}

                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>Asset Name</label>
                        <input
                            type="text"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            placeholder="e.g. Primary Water Pump"
                        />
                    </div>

                    <div className="form-group">
                        <label>Asset Type</label>
                        <input
                            type="text"
                            value={type}
                            onChange={(e) => setType(e.target.value)}
                            placeholder="e.g. Pump"
                        />
                    </div>

                    <div className="form-group">
                        <label>Initial Status</label>
                        <select value={status} onChange={(e) => setStatus(e.target.value)}>
                            <option value="STOPPED">STOPPED</option>
                            <option value="RUNNING">RUNNING</option>
                            <option value="ALARM">ALARM</option>
                        </select>
                    </div>

                    <div className="modal-actions">
                        <button type="button" onClick={onClose} className="btn-cancel">Cancel</button>
                        <button type="submit" className="btn-submit">Save Asset</button>
                    </div>
                </form>
            </div>
        </div>
    );
}