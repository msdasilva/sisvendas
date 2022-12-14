import axios from "axios";
import { toast } from "react-toastify";
import icon from "../../assets/img/notification-icon.svg";
import { BASE_URL } from "../../shared/request";

import "./styles.css";

type Propos = {
  idSale: number;
}

function handleClick(idSale: number) {
  axios.get(`${BASE_URL}/sales/${idSale}/notification`)
  .then(response => {
    toast.info("SMS enviado com sucesso.");
  })
}

function NotificationButton( {idSale} : Propos) {
  return (
    <div className="dsmeta-red-btn" onClick={() => handleClick(idSale) }>
      <img src={icon} alt="Notificar" />
    </div>
  );
}

export default NotificationButton;
