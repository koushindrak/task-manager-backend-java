/*
 *  ***********************************************************************
 *  * VPS CONFIDENTIAL
 *  * ***********************************************************************
 *  *
 *  *  [2017] - [2022] VPS Ltd.
 *  *  All Rights Reserved.
 *  *
 *  * NOTICE:  All information contained herein is, and remains
 *  * the property of VPS Pvt. Ltd, its suppliers (if any), its subsidiaries (if any) and
 *  * Source Code Licensees (if any).  The intellectual and technical concepts contained
 *  * Source Code Licensees (if any) and may be covered by U.S. and Foreign Patents,
 *  * patents in process, and are protected by trade secret or copyright law.
 *  * Dissemination of this information or reproduction of this material
 *  * is strictly forbidden unless prior written permission is obtained
 *  ****************************************************************************
 *
 */

package com.todo.exceptions;

public class AuthenticationException extends ApiException {
    public AuthenticationException(int code, String message){
        super(code,message);
    }
}
