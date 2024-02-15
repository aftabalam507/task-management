import React from "react";
import TaskCard from "../Task/TaskCard/TaskCard";

const TaskList = () => {
  return (
    <div className=" w-[67vw]">
      <div className="space-y-3">
        {[1, 2, 3, 4, 5, 6].map((item) => (
          <TaskCard key={item} />
        ))}
      </div>
    </div>
  );
};

export default TaskList;
