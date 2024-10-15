import { Link } from "react-router-dom";
import logo from "@/assets/letter-x-alphabet-in-brush-style-png.png";
import { useTranslation } from "react-i18next";

export function NavBar() {
    const { t } = useTranslation();
  return (
    <nav className="navbar navber-expand bg-body-tertiary shadow-sm">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/">
          <img src={logo} width={60} />
          ProjectX
        </Link>
        <ul className="navbar-nav">
          <li className="nav-item">
            <Link className="nav-link" to="/signup">
              {t("signUp")}
            </Link>
          </li>
        </ul>
      </div>
    </nav>
  );
}
