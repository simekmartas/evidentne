import React from 'react';
import { Link, Outlet, useLocation } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import { 
  HomeIcon, 
  UserGroupIcon, 
  CalendarIcon, 
  ClipboardDocumentListIcon,
  ViewColumnsIcon,
  ArrowRightOnRectangleIcon 
} from '@heroicons/react/24/outline';

const navigation = [
  { name: 'Dashboard', href: '/dashboard', icon: HomeIcon },
  { name: 'Klienti', href: '/clients', icon: UserGroupIcon },
  { name: 'Kalendář', href: '/calendar', icon: CalendarIcon },
  { name: 'Úkoly', href: '/tasks', icon: ClipboardDocumentListIcon },
  { name: 'Workflow', href: '/workflow', icon: ViewColumnsIcon },
];

function Layout() {
  const { user, logout } = useAuth();
  const location = useLocation();

  const getRoleName = (role: string) => {
    switch (role) {
      case 'VEDOUCI':
        return 'Vedoucí';
      case 'PORADCE':
        return 'Poradce';
      case 'ASISTENT':
        return 'Asistent';
      default:
        return role;
    }
  };

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Sidebar */}
      <div className="fixed inset-y-0 z-50 flex w-72 flex-col">
        <div className="flex grow flex-col gap-y-5 overflow-y-auto bg-gray-900 px-6 pb-4">
          <div className="flex h-16 shrink-0 items-center">
            <h1 className="text-xl font-bold text-white">CRM Systém</h1>
          </div>
          <nav className="flex flex-1 flex-col">
            <ul role="list" className="flex flex-1 flex-col gap-y-7">
              <li>
                <ul role="list" className="-mx-2 space-y-1">
                  {navigation.map((item) => {
                    const isActive = location.pathname.startsWith(item.href);
                    return (
                      <li key={item.name}>
                        <Link
                          to={item.href}
                          className={`
                            group flex gap-x-3 rounded-md p-2 text-sm leading-6 font-semibold
                            ${isActive 
                              ? 'bg-gray-800 text-white' 
                              : 'text-gray-400 hover:text-white hover:bg-gray-800'
                            }
                          `}
                        >
                          <item.icon
                            className={`h-6 w-6 shrink-0 ${isActive ? 'text-white' : 'text-gray-400'}`}
                            aria-hidden="true"
                          />
                          {item.name}
                        </Link>
                      </li>
                    );
                  })}
                </ul>
              </li>
              <li className="mt-auto">
                <div className="mb-4 px-2">
                  <div className="text-xs text-gray-400">Přihlášen jako</div>
                  <div className="text-sm font-medium text-white">{user?.firstName} {user?.lastName}</div>
                  <div className="text-xs text-gray-400">{getRoleName(user?.role || '')}</div>
                </div>
                <button
                  onClick={logout}
                  className="group flex gap-x-3 rounded-md p-2 text-sm leading-6 font-semibold text-gray-400 hover:text-white hover:bg-gray-800 w-full"
                >
                  <ArrowRightOnRectangleIcon
                    className="h-6 w-6 shrink-0"
                    aria-hidden="true"
                  />
                  Odhlásit se
                </button>
              </li>
            </ul>
          </nav>
        </div>
      </div>

      {/* Main content */}
      <div className="pl-72">
        <main className="py-10">
          <div className="px-4 sm:px-6 lg:px-8">
            <Outlet />
          </div>
        </main>
      </div>
    </div>
  );
}

export default Layout; 