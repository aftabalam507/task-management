import { Avatar } from "@mui/material";
import "./Navbar.css";
import React from "react";

const Navbar = () => {
  return (
    <div className="">
      <div className="containers  z-10 fixed  left-0 right-0 top-0 py-3 px-5 lg:px-10,  flex justify-between items-center ">
        <p className="font-bold text-lg">Task Management</p>

        <div className="flex items-center gap-5">
          <p>aftab alam</p>
          <Avatar
            className="bg-#c24dd0"
            src="https://pbs.twimg.com/profile_images/1700112215695560704/RKKebm_W_400x400.jpg"
          />
        </div>
      </div>
    </div>
  );
};

export default Navbar;
