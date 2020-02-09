/*
Copyright (c) 2017 Craig MacFarlane

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Craig MacFarlane nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package com.qualcomm.hardware.lynx.commands.core;

import android.content.Context;

import com.qualcomm.hardware.lynx.LynxI2cDeviceSynch;
import com.qualcomm.hardware.lynx.LynxI2cDeviceSynchV1;
import com.qualcomm.hardware.lynx.LynxI2cDeviceSynchV2;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.util.RobotLog;

/**
 * LynxFirmwareVersionManager
 *
 * The idea is that we localize all application dependencies to firmware
 * versions within this class.  There should be no branching within other
 * areas of the SDK dependent upon firmware versions.  That should all be
 * here thereby allowing the developer to see at a glance what is supported
 * by which versions of the firmware.
 */
public class LynxFirmwareVersionManager
{
    /************************************************************************************************************
     * All classes that need different implementations for varying firmware revisions have a factory method here.
     ***********************************************************************************************************/

    public static LynxI2cDeviceSynch createLynxI2cDeviceSynch(Context context, LynxModule module, int bus)
    {
        if (module.isCommandSupported(LynxI2cWriteReadMultipleBytesCommand.class)) {
            RobotLog.i("LynxFirmwareVersionManager: LynxI2cDeviceSynchV2");
            return new LynxI2cDeviceSynchV2(context, module, bus);
        } else {
            RobotLog.i("LynxFirmwareVersionManager: LynxI2cDeviceSynchV1");
            return new LynxI2cDeviceSynchV1(context, module, bus);
        }
    }
}
