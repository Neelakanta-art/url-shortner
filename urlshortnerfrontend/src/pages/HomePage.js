import React, { useState } from "react";
import axios from "axios";

const HomePage = () => {
  const [originalUrl, setOriginalUrl] = useState("");
  const [shortUrl, setShortUrl] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setShortUrl("");

    try {
      const response = await axios.post("http://localhost:8080/api/create", {
        originalUrl: originalUrl,
        expirationDays: 7, // Example: default expiration in 7 days
      });
      setShortUrl(response.data.shortUrl);
    } catch (err) {
      setError("Failed to create short URL. Please try again.");
    }
  };

  return (
    <div className="container mt-5">
      <h1>URL Shortener</h1>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="urlInput" className="form-label">
            Enter URL:
          </label>
          <input
            type="url"
            className="form-control"
            id="urlInput"
            value={originalUrl}
            onChange={(e) => setOriginalUrl(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Shorten URL
        </button>
      </form>
      {shortUrl && (
        <div className="mt-3">
          <h5>Shortened URL:</h5>
          <a href={shortUrl} target="_blank" rel="noopener noreferrer">
            {shortUrl}
          </a>
        </div>
      )}
      {error && <p className="text-danger mt-3">{error}</p>}
    </div>
  );
};

export default HomePage;
