import React from "react";

const TaskCard = () => {
  return (
    <div>
      <div className="card lg:flex justify-between">
        <div className="lg:flex gap-5 item-center space-y-2 w-[90%] lg:w-[70%]">
          <div className="">
            <img
              className="lg:w-[7rem] lg:h-[rem] object-cover"
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqWm6ls6F0Ilx6H87e5O4QHBkxn4cfQRwKy8r3z0VZ4g&s"
              alt="img"
            />
          </div>
          <div className="space-y-5">
            <div className="space-y-2">
              <h1 className="font-bold text-lg ">Car Rental Website</h1>
              <p className="text-gray-500 text-sm">
                use latest framework and technology to make this website
              </p>
            </div>

            <div className="flex flex-wrap gap-2 items-center">
              {[1, 2, 3, 4].map((item) => (
                <span
                  key={item}
                  className="py-1 px-5 rounded-full techStack techStack"
                >
                  React
                </span>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default TaskCard;
